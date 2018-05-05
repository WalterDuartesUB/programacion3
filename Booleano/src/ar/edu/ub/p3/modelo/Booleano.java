package ar.edu.ub.p3.modelo;

public enum Booleano {
	VERDADERO{
		@Override
		public Booleano not() {
			return FALSO;
		}

		@Override
		public Booleano or(Booleano b) {
			return this;
		}

		@Override
		public Booleano and(Booleano b) {
			return b;
		}

		@Override
		public Booleano equals(Booleano b) {
			return b;
		}

		@Override
		public String toString() {	
			return "V";
		}			
	},
	FALSO{
		@Override
		public Booleano not() {
			return VERDADERO;
		}

		@Override
		public Booleano or(Booleano b) {
			return b;
		}

		@Override
		public Booleano and(Booleano b) {
			return this;
		}

		@Override
		public Booleano equals(Booleano b) {
			return b.not();
		}
		
		@Override
		public String toString() {	
			return "F";
		}			
	};
	
	
	public abstract Booleano not();
	public abstract Booleano or( Booleano b );
	public abstract Booleano and( Booleano b );
	public abstract Booleano equals( Booleano b );
	
	public Booleano xor(Booleano b) {
		return this.equals(b).not();
	}	
}
