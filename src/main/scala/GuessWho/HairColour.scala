package GuessWho

sealed trait HairColour {
  case object BLONDE extends HairColour
  case object BRUNETTE extends HairColour
  case object RED extends HairColour
}
