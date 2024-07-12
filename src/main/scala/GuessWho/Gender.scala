package GuessWho

sealed trait Gender {
  case object MALE extends Gender
  case object FEMALE extends Gender
}
