class Operation
{
	public int operande1;
	public int operande2;
	public char operateur;
	private int resultat;

	public Operation(int operande1, int operande2, char operateur)
	{
		this.operande1 = operande1;
		this.operande2 = operande2;
		this.operateur = operateur;

		switch (this.operateur)
		{
			case 'a':
				try
				{
					this.addition();
				}
				catch (DepassementCapaciteException e)
				{
					System.err.print("La somme des opérandes est trop grande%n");
				}
				break;
			case 's':
				this.soustraction();
			case 'm':
				try
				{
					this.multiplication();
				}
				catch (DepassementCapaciteException e)
				{
					System.err.print("Le produit des opérandes est trop grand%n");
				}
				break;
			case 'd':
				try
				{
					this.division();
				}
				catch (DivisionParZeroException e)
				{
					System.err.print("Le diviseur ne peut pas être zéro");
				}
				break;
			default:
				System.err.print("Operateur non disponible");
		}

		System.out.printf("%d%n", this.resultat);
	}

	public void addition() throws DepassementCapaciteException
	{
		if ((Integer.MAX_VALUE - this.operande1) < this.operande2)
		{
			throw new DepassementCapaciteException();
		}

		else
		{
			this.resultat = operande1 + operande2;
		}
	}

	public void soustraction()
	{
		this.resultat = this.operande1 - this.operande2;
	}

	public void multiplication() throws DepassementCapaciteException
	{
		if ((Integer.MAX_VALUE / this.operande1) > this.operande2)
		{
			throw new DepassementCapaciteException();
		}
		else
		{
			this.resultat = this.operande1 * this.operande2;
		}
	}

	public void division() throws DivisionParZeroException
	{
		if (this.operande2 == 0)
		{
			throw new DivisionParZeroException();
		}
	}
}
class DepassementCapaciteException extends Exception
{
	public DepassementCapaciteException()
	{
		super("DepassementCapaciteException");
	}
}

class DivisionParZeroException extends Exception
{
	public DivisionParZeroException()
	{
		super("DivisionParZeroException");
	}
}

class Main
{
	public static void main(String[] args)
	{
		Operation test1 = new Operation(10, Integer.MAX_VALUE, 'm');
	}
}
