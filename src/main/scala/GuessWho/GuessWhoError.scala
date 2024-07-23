package GuessWho

sealed class GuessWhoError(errorMessage:String){
  def printErrorMessage():Unit = {println(errorMessage)}
}

object GuessWhoError{
  case object SomethingWentWrongError extends GuessWhoError("Something strange went wrong!")
  case object AttributeNotInteger extends GuessWhoError("Please enter a number")
  case object AttributeOutOfRange extends GuessWhoError("Please enter a number between 0 to 8")
}
