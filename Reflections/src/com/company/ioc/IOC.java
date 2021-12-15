package com.company.ioc;


/**
 * Issues we may counter
 * what will happen if I have multiple users in different places and I decided to
 * use update the user constructor adding or removing dependencies in this case
 * we can define the user in the xml file as well and use and let the instantiation of it
 * to the xml but on this case will use @AutoWired to let the xml knows that we expect some action to happen
 */
/**
 * .xml Configuration
 * <bean id="MySql"  class "com.company.ioc.MySQLDatabase"/>
 * <bean id="Oracle"  class "com.company.ioc.OracleDatabase"/>
 * */


public class IOC {
    public static void main(String[] args) {
        IOC container = new IOC();

        User user =  container.new User(container.new OracleDatabase());
        user.add("Hello ! ");

        //@AutoWired
        User user2;
    }

    public class User{
        Database database;
        public User(Database database) {
            this.database = database;
        }

       public void add(String data){
            database.persist(data);
       }
    }

    public class MySQLDatabase implements Database{

        @Override
        public void persist(String data) {
            System.out.println("Configure By MySQLDatabase "+data);
        }
    }

    public class OracleDatabase implements Database {

        @Override
        public void persist(String data) {
            System.out.println("Configure By OracleDatabase "+data);
        }
    }

    public interface Database{
        public void persist(String data);
    }

}
