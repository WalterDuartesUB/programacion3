package ar.edu.ub.p3.modelo;

class Scissors extends Element {

	Scissors()	{
		
	}
	
	@Override
	public Element compare(Element element) {
		return element.compare( this );
	}

	@Override
	public String toString() {
		return "Scissors";
	}

	@Override
	protected Element compare(Rock aRock) {
		return aRock;
	}

	@Override
	protected Element compare(Scissors aScissors) {
		return this;
	}

	@Override
	protected Element compare(Paper aPaper) {
		return this;
	}

}
