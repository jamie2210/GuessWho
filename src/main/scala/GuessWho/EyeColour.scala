package GuessWho

sealed trait EyeColour {
  case object BLUE extends EyeColour
  case object GREEN extends EyeColour
  case object BROWN extends EyeColour
}