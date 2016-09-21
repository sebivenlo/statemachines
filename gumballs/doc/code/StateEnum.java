public enum StateEnum implements State {
  NoCoin {
    @Override
      public void insertCoin( Context gbc ) {
      gbc.getOutput().println( "You inserted a coin" );
      gbc.setState( HasCoin );
    }
    // enum values left out
  }
  // example default methods
  @Override
  public void draw( Context gbc ) {
    gbc.getOutput().println( reason() );
  }

  @Override
  public void insertCoin( Context gbc ) {
    gbc.getOutput().println( reason() );
  }

  @Override
  public void refill( Context gbm ) {
    gbm.getOutput().println(reason());
  }
}
