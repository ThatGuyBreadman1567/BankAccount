import java.util.ArrayList;

/**
 * 
 * @author breadman22
 *
 */
public class BankAccountTester 
{
	static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	public static void main(String[] args) throws IllegalArgumentException
	{
		
		BankAccount BenS = new SavingsAccount("Ben", 5000,  0.05, 100, 30);
		BankAccount BenC = new CheckingAccount("Ben", 5000, 30, 5, 10);
		accounts.add(BenS);
		accounts.add(BenC);
		try
		{
			getAccountByNumber(2).transfer(getAccountByNumber(1), 500);
		}
		catch(IllegalArgumentException a)
		{
			System.out.println("NOT AUTHORIZED");
		}
		for(BankAccount a : accounts)
		{
			System.out.println(a.toString());
		}
//		try
//		{
//			BenC.transfer(BenS, 500);
//		}
//		catch(IllegalArgumentException a)
//		{
//			System.out.println("Not Authorized");;
//		}
//		System.out.println(BenC.toString() + "\n" + BenS.toString());
//		try
//		{
//			BenS.transfer(BenC, 5500);
//		}
//		catch(IllegalArgumentException a)
//		{
//			System.out.println("Not Authorized");;
//		}
//		System.out.println(BenC.toString() + "\n" + BenS.toString());
//		try
//		{
//			BenC.transfer(BenS, 10000);
//		}
//		catch(IllegalArgumentException a)
//		{
//			System.out.println("Not Authorized");;
//		}
//		System.out.println(BenC.toString() + "\n" + BenS.toString());
	}
	private static BankAccount getAccountByNumber(int number)
	{
		for(BankAccount a : accounts)
		{
			if(a.getAccountNum()==number)
				return a;
		}
		throw new IllegalArgumentException();
	}
	private static ArrayList<BankAccount> getAccountByName(String name)
	{
		ArrayList<BankAccount> names = new ArrayList<BankAccount>();
		
		for(BankAccount a : accounts)
		{
			if(a.getName().equals(name))
				names.add(a);
		}
		if(names.size()>1)
			return names;
		return null;
	}
}
