package ar.edu.ub.p3.modelo;

class Paper extends Element {
	
	Paper()	{
		
	}
	
	@Override
	public Element compare(Element element) {
		return element.compare( this );
	}

	@Override
	public String toString() {
		return "Paper";
	}

	@Override
	protected Element compare(Rock aRock) {
		return this;
	}

	@Override
	protected Element compare(Scissors aScissors) {
		return aScissors;
	}

	@Override
	protected Element compare(Paper aPaper) {
		return this;
	}

}
