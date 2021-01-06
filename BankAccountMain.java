import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Benjamin Readman
 *
 */
public class BankAccountMain 
{
	static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	
	private static double OVER_DRAFT_FEE = 15;
	private static double RATE = .0025;
	private static double TRANSACTION_FEE = 1.5;
	private static double MIN_BAL = 300;
	private static double MIN_BAL_FEE = 10;
	private static int FREE_TRANSACTIONS = 10;
	
	public static void main(String[] args) throws IllegalArgumentException
	{
		Scanner in = new Scanner(System.in);
		
		System.out.print("Would you like to (A)dd an account, (M)ake a transaction, or (T)erminate the program? ");
		String loop = in.nextLine();
		if(!loop.equals("A") || !loop.equals("M") || !loop.equals("T"))
		{
			try
			{
				throw new IllegalArgumentException();
			}
			catch(IllegalArgumentException a)
			{}
		}
		while(!loop.equals("T"))
		{
			if(loop.equals("A"))
			{
				System.out.print("Would you like to make a (S)avings account or a (C)hecking account? ");
				String accType = in.nextLine();
				if(accType.equals("S"))
				{
					System.out.print("What is the name that you would like on the account? ");
					String name = in.nextLine();
					System.out.print("What is the opening balance on the account? ");
					double bal = in.nextInt();
					in.nextLine();
					accounts.add(new SavingsAccount(name, bal, RATE, MIN_BAL, MIN_BAL_FEE));
				}
				else if(accType.equals("C"))
				{
					System.out.print("What is the name that you would like on the account? ");
					String name = in.nextLine();
					System.out.print("What is the opening balance on the account? ");
					double bal = in.nextInt();
					in.nextLine();
					accounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				}
			}
			else if(loop.equals("M"))
			{
				System.out.print("Would you like to (D)eposit, (W)ithdraw, (T)ransfer, or (G)et account numbers? ");
				String transaction = in.nextLine();
				
				switch(transaction)
				{
					case "D":
					{
						System.out.print("What is the account number? ");
						int accNum = in.nextInt();
						in.nextLine();
						System.out.print("How much would you like to deposit? ");
						double amt = in.nextDouble();
						in.nextLine();
						
						if(getAccountByNumber(accNum)==null)
							break;
						else
						{
							try
							{
								getAccountByNumber(accNum).deposit(amt);
							}
							catch(IllegalArgumentException a)
							{
								System.out.println("transaction was not authorized");
							}
						}
						break;
					}
					case "W":
					{
						System.out.print("What is the account number? ");
						int accNum = in.nextInt();
						in.nextLine();
						System.out.print("How much would you like to deposit? ");
						double amt = in.nextDouble();
						in.nextLine();
						
						if(getAccountByNumber(accNum)==null)
							break;
						else
						{
							try
							{
								getAccountByNumber(accNum).withdraw(amt);
							}
							catch(IllegalArgumentException a)
							{
								System.out.println("transaction was not authorized");
							}
						}
						break;
					}
					case "T":
					{
						System.out.print("What is the account number? ");
						int accNum = in.nextInt();
						in.nextLine();
						System.out.print("How much would you like to transfer? ");
						double amt = in.nextDouble();
						in.nextLine();
						System.out.print("What is the account number you would like to transfer to? ");
						int accTrans = in.nextInt();
						in.nextLine();
						
						if(!(getAccountByNumber(accNum)==null) && !(getAccountByNumber(accTrans)==null))
						{
							try
							{
								getAccountByNumber(accNum).transfer(getAccountByNumber(accTrans), amt);
							}
							catch(IllegalArgumentException a)
							{
								System.out.println("transaction was not authorized");
							}
						}
						break;
					}
					case "G":
					{
						System.out.print("What is the name on the account? ");
						String accName = in.next();
						ArrayList<BankAccount> names = getAccountByName(accName);
						for(BankAccount a : names)
						{
							System.out.println(a.toString());
						}
						break;
					}
					default:
					{
						break;
					}
				}
					
						
			}
			System.out.print("Would you like to (A)dd an account, (M)ake a transaction, or (T)erminate the program? ");
			loop = in.nextLine();
		}
		for(BankAccount a : accounts)
		{
			System.out.println(a.toString());
		}
	}
	
	private static BankAccount getAccountByNumber(int number)
	{
		for(BankAccount a : accounts)
		{
			if(a.getAccountNum()==number)
				return a;
		}
		return null;
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
