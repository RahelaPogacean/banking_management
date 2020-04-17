import businessLayer.BankingOperations;
import dataClasses.Account;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


    public class BankingOperationsTest {

        @Test
        public void withdrawTest() {

            Account a1 = new Account(9);
            Account a2 = new Account(3);

            a1.setAmount(5000);
            a2.setAmount(9500);
            BankingOperations b = new BankingOperations();
            b.withdraw(9, 3,  500);

            assertEquals(5500, a1.getAmount(), 5500);
            assertEquals(9000, a2.getAmount(), 9000);
        }

        @Test
        public void depositTest(){

            Account a1 = new Account(2);
            Account a2 = new Account(45);

            a1.setAmount(900);
            a2.setAmount(2100);
            BankingOperations b = new BankingOperations();
            b.deposit(2, 45,  100);

            assertEquals(800, a1.getAmount(), 800);
            assertEquals(2200, a2.getAmount(), 2200);
        }
        @Test
        public void payBillsTest(){

            Account a1 = new Account(77);
            a1.setAmount(7800);

            BankingOperations b = new BankingOperations();
            b.payBills(200, 77, "gas");
            assertEquals(7600, a1.getAmount(), 7600);
        }

    }
