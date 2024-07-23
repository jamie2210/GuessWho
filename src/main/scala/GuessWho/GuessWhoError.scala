package GuessWho

sealed class GuessWhoError(errorMessage:String)

object GuessWhoError{
  case object SomethingWentWrongError extends GuessWhoError("Something strange went wrong!")
  case object GoBackError extends GuessWhoError("Chosen to go back - please select a new option")
  case object AttributeNotInteger extends GuessWhoError("Please enter a number")
  case object AttributeOutOfRange extends GuessWhoError("Please enter a number between 0 to 8")
}
