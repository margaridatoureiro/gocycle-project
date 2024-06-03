/*
MIT License

Copyright (c) 2024, Nuno Datia, Matilde Pato, ISEL

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
package isel.sisinf.ui;

import isel.sisinf.jpa.BicicletaDAL.IBicicletaRepository;
import isel.sisinf.jpa.JPAContext;
import isel.sisinf.jpa.PessoaDAL.IPessoaRepository;
import isel.sisinf.model.Bicicleta;
import isel.sisinf.model.Pessoa;

import java.util.*;


interface DbWorker
{
    void doWork();
}
class UI
{
    private enum Option
    {
        // DO NOT CHANGE ANYTHING!
        Unknown,
        Exit,
        createCostumer,
        listExistingBikes,
        checkBikeAvailability,
        obtainBookings,
        makeBooking,
        cancelBooking,
        about
    }
    private static UI __instance = null;
  
    private HashMap<Option,DbWorker> __dbMethods;

    private UI()
    {
        // DO NOT CHANGE ANYTHING!
        __dbMethods = new HashMap<Option,DbWorker>();
        __dbMethods.put(Option.createCostumer, () -> {
            try {
                UI.this.createCostumer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        __dbMethods.put(Option.listExistingBikes, () -> UI.this.listExistingBikes()); 
        __dbMethods.put(Option.checkBikeAvailability, () -> UI.this.checkBikeAvailability());
        __dbMethods.put(Option.obtainBookings, new DbWorker() {public void doWork() {UI.this.obtainBookings();}});
        __dbMethods.put(Option.makeBooking, new DbWorker() {public void doWork() {UI.this.makeBooking();}});
        __dbMethods.put(Option.cancelBooking, new DbWorker() {public void doWork() {UI.this.cancelBooking();}});
        __dbMethods.put(Option.about, new DbWorker() {public void doWork() {UI.this.about();}});

    }

    public static UI getInstance()
    {
        // DO NOT CHANGE ANYTHING!
        if(__instance == null)
        {
            __instance = new UI();
        }
        return __instance;
    }

    private Option DisplayMenu()
    {
        Option option = Option.Unknown;
        Scanner s = new Scanner(System.in); //Scanner closes System.in if you call close(). Don't do it
        try
        {
            // DO NOT CHANGE ANYTHING!
            System.out.println("Bicycle reservation");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. Create Costumer");
            System.out.println("3. List Existing Bikes");
            System.out.println("4. Check Bike Availability");
            System.out.println("5. Current Bookings");
            System.out.println("6. Make a booking");
            System.out.println("7. Cancel Booking");
            System.out.println("8. About");
            System.out.print(">");
            int result = s.nextInt();
            option = Option.values()[result];
        }
        catch(RuntimeException ex)
        {
            //nothing to do.
        }
        
        return option;

    }
    private static void clearConsole() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");
    }

    public void Run() throws Exception
    {
        // DO NOT CHANGE ANYTHING!

        Option userInput;
        do
        {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try
            {
                __dbMethods.get(userInput).doWork();
                System.in.read();
            }
            catch(NullPointerException ex)
            {
                //Nothing to do. The option was not a valid one. Read another.
            }

        }while(userInput!=Option.Exit);
    }

    /**
    To implement from this point forward. Do not need to change the code above.
    -------------------------------------------------------------------------------     
        IMPORTANT:
    --- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
    --- Other Methods and properties can be added to support implementation -------
    -------------------------------------------------------------------------------
    
    */

    private static final int TAB_SIZE = 24;

    static String inputData(String prompt) {
        System.out.println(prompt);
        System.out.print(">");

        return new Scanner(System.in).nextLine();
    }

    // FUNCTION TO DISPLAY IN A TABLE FORMAT
    public static void displayTable(List<String> headers, List<List<Object>> rows) {
        // Calculate the maximum length for each column
        int numColumns = headers.size();
        int[] maxLengths = new int[numColumns];
        for (int i = 0; i < numColumns; i++) {
            maxLengths[i] = headers.get(i).length();
        }
        for (List<Object> row : rows) {
            for (int i = 0; i < numColumns; i++) {
                String cellContent = row.get(i).toString();
                maxLengths[i] = Math.max(maxLengths[i], cellContent.length());
            }
        }
        // Print headers
        for (int i = 0; i < numColumns; i++) {
            String header = headers.get(i);
            System.out.print(header);
            printSpaces(maxLengths[i] - header.length() + 2); // Add 2 extra spaces for padding
        }
        System.out.println();

        // Print rows
        for (List<Object> row : rows) {
            for (int i = 0; i < numColumns; i++) {
                String cellContent = row.get(i).toString();
                System.out.print(cellContent);
                printSpaces(maxLengths[i] - cellContent.length() + 2); // Add 2 extra spaces for padding
            }
            System.out.println();
        }
    }

    // Helper function to print spaces
    private static void printSpaces(int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            System.out.print(" ");
        }
    }


    private void createCostumer() throws Exception {
        System.out.println("createCustomer()");

        // Collecting all necessary data
        String name = inputData("Input name");
        String address = inputData("Input address");
        String email = inputData("Input email");
        String phone = inputData("Input phone number");
        String idNumber = inputData("Input identification number");
        String nationality = inputData("Input nationality");
        String disciplinaryAttribute = "C";

        // Creating a new Pessoa object
        Pessoa customer = new Pessoa(name, address, email, phone, idNumber, nationality, disciplinaryAttribute);

        // Creating a new JPA context and initializing the repository to create a customer
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            IPessoaRepository repo = ctx.getPessoaRepository();
            repo.create(customer);
            ctx.commit();
            System.out.println("Customer created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();
        }

    }
  
    private void listExistingBikes()
    {
        System.out.println("listExistingBikes()");
        // Creating a new JPA context and initializing the repository
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            IBicicletaRepository repo = ctx.getBicicletaRepository();
            // Getting all bikes from repo
            List<Bicicleta> rows = repo.getAll().stream().toList();

            // Creating table headers and rows to print
            List<String> headers = List.of("Id", "Peso", "Raio", "Modelo", "Marca", "Mudanca", "Estado", "Atrdisc", "Dispositivo");
            List<List<Object>> formattedRows = new ArrayList<>();

            for (Bicicleta bicicleta : rows) {
                List<Object> row = new ArrayList<>();
                row.add(bicicleta.getId());
                row.add(bicicleta.getPeso());
                row.add(bicicleta.getRaio());
                row.add(bicicleta.getModelo());
                row.add(bicicleta.getMarca());
                row.add(bicicleta.getMudanca());
                row.add(bicicleta.getEstado());
                row.add(bicicleta.getAtrdisc());
                row.add(bicicleta.getNoserie());
                formattedRows.add(row);
            }

            // Displaying the obtained rows in a nice format
            displayTable(headers, formattedRows);
            ctx.commit();

        } catch (Exception e) {
            System.err.println("Error listing bikes: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void checkBikeAvailability()
    {
        // TODO
        System.out.println("checkBikeAvailability()");

    }

    private void obtainBookings() {
        // TODO
        System.out.println("obtainBookings()");
    }

    private void makeBooking()
    {
        // TODO
        System.out.println("makeBooking()");
        
    }

    private void cancelBooking()
    {
        // TODO
        System.out.println("cancelBooking");
        
    }
    private void about()
    {
        System.out.println("Group members:");
        System.out.println(" - Manuel Fonseca");
        System.out.println(" - Margarida Toureiro");
        System.out.println(" - Ricardo Almeida");
        
    }
}



public class App{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception{
        try {
            UI.getInstance().Run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }
}
