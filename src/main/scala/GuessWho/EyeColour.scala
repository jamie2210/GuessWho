package GuessWho

sealed trait EyeColour

object EyeColour {
  case object BLUE extends EyeColour
  case object GREEN extends EyeColour
  case object BROWN extends EyeColour
}