package GuessWho


sealed trait Gender

object Gender{
  case object MALE extends Gender
  case object FEMALE extends Gender
}
