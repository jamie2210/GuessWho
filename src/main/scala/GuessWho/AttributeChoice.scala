package GuessWho

sealed class AttributeChoice

object AttributeChoice{
  case object NameChoice extends AttributeChoice
  case object HairChoice extends AttributeChoice
  case object FacialHairChoice extends AttributeChoice
  case object GlassesChoice extends AttributeChoice
  case object HatChoice extends AttributeChoice
  case object GenderChoice extends AttributeChoice
  case object EyesChoice extends AttributeChoice
  case object HintChoice extends AttributeChoice
}
