package GuessWho


sealed class Question

object Question{
  case object hasHairQuestion extends Question
  case object hasGlassesQuestion extends Question
  case object hasHatQuestion extends Question
  case object hasFacialHairQuestion extends Question
}
