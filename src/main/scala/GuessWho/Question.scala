package GuessWho


sealed class Question



object Question{
  sealed trait HintQuestion extends Question
  sealed class EyeColourQuestion extends Question
  sealed trait HairColourQuestion extends Question
  sealed trait GenderQuestion extends Question
  sealed trait GoBack extends Question
  sealed class NameQuestion(name:String) extends Question


  case object hasHairQuestion extends Question
  case object BlondeHairQuestion extends HairColourQuestion
  case object BrunetteHairQuestion extends HairColourQuestion
  case object RedHairQuestion extends HairColourQuestion

  case object hasGlassesQuestion extends Question

  case object hasHatQuestion extends Question

  case object hasFacialHairQuestion extends Question

  case object MaleGenderQuestion extends GenderQuestion
  case object FemaleGenderQuestion extends GenderQuestion

  case object BlueEyesQuestion extends EyeColourQuestion
  case object BrownEyesQuestion extends EyeColourQuestion
  case object GreenEyesQuestion extends EyeColourQuestion

  case object RemoveCharacterHint extends HintQuestion
  case object LetterFromNameHint extends HintQuestion

  case object GoBackOption extends GoBack
}
