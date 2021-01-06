/**
 * 
 * @author Benjamin Readman
 *
 */
public class SavingsAccount extends BankAccount
{
	static double MIN_BAL;
	static double MIN_BAL_FEE;
	static int MAX_TRANSFER=6;
	static double TRANSACTION_FEE=10;
	
	private double intRate;
	private int numTransfers = 0;
	
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	public void deposit(double amt) throws IllegalArgumentException
	{
		if(amt<=0)
			throw new IllegalArgumentException();
		else
			super.deposit(amt);
	}
	public void withdraw(double amt) throws IllegalArgumentException
	{
		if(amt<=0 || getBalance()-amt<0)
			throw new IllegalArgumentException();
		else
		{
			super.withdraw(amt);
			if(getBalance()<MIN_BAL)
				super.withdraw(MIN_BAL_FEE);
		}
	}
	public void transfer(BankAccount other, double amt) throws IllegalArgumentException
	{
		if(amt<=0 || other.getName()!=getName())
			throw new IllegalArgumentException();
		else
		{
			numTransfers++;
			withdraw(amt);
			other.deposit(amt);
			if(numTransfers>MAX_TRANSFER)
				withdraw(TRANSACTION_FEE);
		}
	}
	public void addInterest()
	{
		super.deposit(intRate*getBalance());
	}
	public void endOfMonthUpdate()
	{
		addInterest();
		numTransfers=0;
	}
}
