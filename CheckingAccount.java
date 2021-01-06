/**
 * 
 * @author Benjamin Readman
 *
 */
public class CheckingAccount extends BankAccount
{
	static double OVER_DRAFT_FEE;
	static double TRANSACTION_FEE;
	static int FREE_TRANS;
	
	private int numTransactions = 0;
	
	CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	public void deposit(double amt) throws IllegalArgumentException
	{
		if(amt <= 0)
			throw new IllegalArgumentException();
		else
		{
			numTransactions++;
			super.deposit(amt);
			if(numTransactions>FREE_TRANS)
				super.withdraw(TRANSACTION_FEE);
			if(getBalance()<0)
				super.withdraw(OVER_DRAFT_FEE);
		}
	}
	public void withdraw(double amt) throws IllegalArgumentException
	{
		if(amt <= 0)
			throw new IllegalArgumentException();
		else
		{
			if(getBalance()>0)
			{
				numTransactions++;
				super.withdraw(amt);
				if(numTransactions>FREE_TRANS)
					super.withdraw(TRANSACTION_FEE);
				if(getBalance()<0)
					super.withdraw(OVER_DRAFT_FEE);
			}
			
		}
	}
	public void transfer(BankAccount other, double amt) throws IllegalArgumentException
	{
		if(amt<=0 || other.getName()!=getName() || getBalance()-amt<0)
			throw new IllegalArgumentException();
		else
		{
			withdraw(amt);
			other.deposit(amt);
		}
	}
	public void endOfMonthUpdate()
	{
		numTransactions=0;
	}
}
