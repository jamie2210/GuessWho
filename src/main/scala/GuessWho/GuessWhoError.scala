package GuessWho

sealed class GuessWhoErrors(errorMessage:String)

object GuessWhoErrors{
  case object SomethingWentWrongError extends GuessWhoErrors("Something strange went wrong!")
  case object GoBackError extends GuessWhoErrors("Chosen to go back - please select a new option")

}
