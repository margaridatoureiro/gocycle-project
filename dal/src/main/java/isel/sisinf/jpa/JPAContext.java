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

import java.util.Collection;
import java.util.List;

import isel.sisinf.jpa.BicicletaDAL.IBicicletaRepository;
import isel.sisinf.jpa.PessoaDAL.IPessoaRepository;
import isel.sisinf.model.Bicicleta;
import isel.sisinf.model.Pessoa;
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

	private IPessoaRepository _pessoaRepository;
	private IBicicletaRepository _bicicletaRepository;

    
/// HELPER METHODS    
    protected List helperQueryImpl(String jpql, Object... params)
    {
    	Query q = _em.createQuery(jpql);

		for(int i = 0; i < params.length; ++i)
			q.setParameter(i+1, params[i]);
		
		return q.getResultList();
    }
    
    protected Object helperCreateImpl(Object entity)
    {
    	beginTransaction();
		_em.persist(entity);
		commit();
		return entity;
    }
    
    protected Object helperUpdateImpl(Object entity)
    {
    	beginTransaction();
		_em.merge(entity);
		commit();
		return entity;	
    }
    
    protected Object helperDeleteteImpl(Object entity)
    {
    	beginTransaction();
		_em.remove(entity);
		commit();
		return entity;
    }
/// END HELPER

	protected class PessoaRepository implements IPessoaRepository {

		@Override
		public Pessoa create(Pessoa entity) {
			return (Pessoa) helperCreateImpl(entity);
		}

		@Override
		public Collection<Pessoa> getAll() {
			return _em.createNamedQuery("Pessoa.getAll",Pessoa.class)
					.getResultList();
		}

		@Override
		public Pessoa findByKey(Integer key) {
			return _em.createNamedQuery("Pessoa.findByKey", Pessoa.class)
					.setParameter("key", key)
					.getSingleResult();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Pessoa> find(String jpql, Object... params) {
			return helperQueryImpl( jpql, params);
		}

		@Override
		public Pessoa update(Pessoa entity) {
			return (Pessoa) helperUpdateImpl(entity);
		}

		@Override
		public Pessoa delete(Pessoa entity) {
			return (Pessoa) helperDeleteteImpl(entity);
		}

		@Override
		public Pessoa save(Pessoa entity) {
			return null;
		}
	}

	protected class BicicletaRepository implements IBicicletaRepository {

		@Override
		public Bicicleta create(Bicicleta entity) {
			return (Bicicleta) helperCreateImpl(entity);
		}

		@Override
		public Collection<Bicicleta> getAll() {
			return _em.createNamedQuery("Bicicleta.getAll", Bicicleta.class)
					.getResultList();
		}

		@Override
		public Bicicleta findByKey(Integer key) {
			return _em.createNamedQuery("Bicicleta.findByKey", Bicicleta.class)
					.setParameter("key", key)
					.getSingleResult();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Bicicleta> find(String jpql, Object... params) {
			return helperQueryImpl( jpql, params);
		}

		@Override
		public Bicicleta update(Bicicleta entity) {
			return (Bicicleta) helperUpdateImpl(entity);
		}

		@Override
		public Bicicleta delete(Bicicleta entity) {
			return (Bicicleta) helperDeleteteImpl(entity);
		}

		@Override
		public Bicicleta save(Bicicleta entity) {
			return null;
		}
	}

    
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

		this._pessoaRepository = new PessoaRepository();
		this._bicicletaRepository = new BicicletaRepository();
	}


	@Override
	public void close() throws Exception {
		
        if(_tx != null)
        	_tx.rollback();
        _em.close();
        _emf.close();
	}

	@Override
	public IPessoaRepository getPessoaRepository() {
		return _pessoaRepository;
	}

	@Override
	public IBicicletaRepository getBicicletaRepository() {
		return _bicicletaRepository;
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
