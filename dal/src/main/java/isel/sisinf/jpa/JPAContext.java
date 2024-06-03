/*
MIT License

Copyright (c) 2022-2024, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.jpa;

import java.util.List;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

public class JPAContext implements IContext{

    private EntityManagerFactory _emf;
    private EntityManager _em;

    private EntityTransaction _tx;
    private int _txcount;

    
/// HELPER METHODS    
    public List helperQueryImpl(String jpql, Object... params)
    {
    	Query q = _em.createQuery(jpql);

		for(int i = 0; i < params.length; ++i)
			q.setParameter(i+1, params[i]);
		
		return q.getResultList();
    }
    
    public Object helperCreateImpl(Object entity)
    {
    	beginTransaction();
		_em.persist(entity);
		commit();
		return entity;
    }
    
    public Object helperUpdateImpl(Object entity)
    {
    	beginTransaction();
		_em.merge(entity);
		commit();
		return entity;	
    }
    
    public Object helperDeleteteImpl(Object entity)
    {
    	beginTransaction();
		_em.remove(entity);
		commit();
		return entity;
    }
/// END HELPER

	@Override
	public void beginTransaction() {
		if(_tx == null)
		{
			_tx = _em.getTransaction();
			_tx.begin();
			_txcount=0;
		}
		++_txcount;
	}
	
	@Override
	public void beginTransaction(IsolationLevel isolationLevel) 
	{
		beginTransaction();
		Session session =_em.unwrap(Session.class);
		DatabaseLogin databaseLogin = (DatabaseLogin) session.getDatasourceLogin();
		System.out.println(databaseLogin.getTransactionIsolation());
		
		int isolation = DatabaseLogin.TRANSACTION_READ_COMMITTED;
		if(isolationLevel == IsolationLevel.READ_UNCOMMITTED)
			isolation = DatabaseLogin.TRANSACTION_READ_UNCOMMITTED;
		else if(isolationLevel == IsolationLevel.REPEATABLE_READ)
			isolation = DatabaseLogin.TRANSACTION_REPEATABLE_READ;
		else if(isolationLevel == IsolationLevel.SERIALIZABLE)
			isolation = DatabaseLogin.TRANSACTION_SERIALIZABLE;
		
		databaseLogin.setTransactionIsolation(isolation);
	}

	@Override
	public void commit() {
		
		--_txcount;
		if(_txcount==0 && _tx != null)
		{
			_em.flush(); //To assure all changes in memory go into the database
			_tx.commit();
			_tx = null;
		}
	}

	@Override
	public void flush() {
		_em.flush();
	}


	@Override
	public void clear() {
		_em.clear();
		
	}

	@Override
	public void persist(Object entity) {
		_em.persist(entity);
		
	}

	public JPAContext() {
		this("dal-lab");
	}
	
	public JPAContext(String persistentCtx) 
	{
		super();
	
		this._emf = Persistence.createEntityManagerFactory(persistentCtx);
		this._em = _emf.createEntityManager();
	}


	@Override
	public void close() throws Exception {

        if(_tx != null)
        	_tx.rollback();
        _em.close();
        _emf.close();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return _emf;
	}

	public EntityManager getEntityManager() {
		return _em;
	}

/// functions and stored procedure
	//Example using a scalar function
	public java.math.BigDecimal rand_fx(int seed) {
	
		StoredProcedureQuery namedrand_fx = 
		          _em.createNamedStoredProcedureQuery("namedrand_fx");
		namedrand_fx.setParameter(1, seed);
		namedrand_fx.execute();
		
		return (java.math.BigDecimal)namedrand_fx.getOutputParameterValue(2);
	}

}
