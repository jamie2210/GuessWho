package GuessWho

sealed trait HairColour

object HairColour{
  case object BLONDE extends HairColour
  case object BRUNETTE extends HairColour
  case object RED extends HairColour
  case object NONE extends HairColour
}
