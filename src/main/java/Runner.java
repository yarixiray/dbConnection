import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    //STEP 1. Import required packages
        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

        //  Database credentials
        static final String USER = "YARIX";
        static final String PASS = "1111";

        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            try{
                //STEP 2: Register JDBC driver
                Class.forName("oracle.jdbc.driver.OracleDriver");

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();

                //STEP 5: Run a SQL

                List<String> list = new ArrayList<String>();

                String sql;
                String sql2;
//                sql2 = "SELECT id, first, last, age FROM Employees";
                sql = "select customer_id,cust_first_name,cust_last_name,CUST_STREET_ADDRESS1 from demo_customers";
                ResultSet rs = stmt.executeQuery(sql);
                int columnCount = rs.getMetaData().getColumnCount(); //count of fields;
                while (rs.next()){
//                    list.add(rs.getInt("customer_id"));
                    list.add(rs.getString("cust_first_name"));

                }

                for(int i =0; i<list.size(); i++) {
                    System.out.println(list.get(i));
                }
                //STEP 5: Extract data from result set

//                while(rs.next()){
//                    //Retrieve by column name
//                    int customer_id  = rs.getInt("customer_id");
//                    String first = rs.getString("cust_first_name");
//                    String last = rs.getString("cust_last_name");
//
//                    list.add(customer_id,first,last);
//
//                    //Display values
//                    System.out.print("customer_id: " + customer_id);
//                    System.out.print(", cust_first_name: " + first);
//                    System.out.println(", cust_last_name: " + last);
//                }
                //STEP 6: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }//end main
    }//end FirstExample
