/**
 * 
 * @author Benjamin Readman
 *
 */
public abstract class BankAccount 
{
	static int nextAccNum=1;
	
	private String name;
	private int acctNum=0;
	private double balance;
	
	public BankAccount(String n)
	{
		name = n;
		acctNum = nextAccNum;
		balance = 0;
	}
	public BankAccount(String n, double b)
	{
		name = n;
		acctNum = nextAccNum;
		nextAccNum++;
		balance = b;
	}
	
	public void deposit(double amt)
	{
		balance += amt;
	}
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	public int getAccountNum()
	{
		return acctNum;
	}
	public String getName()
	{
		return name;
	}
	public double getBalance()
	{
		return balance;
	}
	public abstract void endOfMonthUpdate();
	public void transfer(BankAccount other, double amt)
	{
		withdraw(amt);
		other.deposit(amt);
	}
	public String toString()
	{
		return acctNum + "\t" + name + "\t$" + balance;
	}
}
