package GuessWho

/** name: String,
 hasHair: Boolean,
 hasFacialHair: Boolean,
 hasGlasses: Boolean,
 hasHat: Boolean,
 gender:Gender,
 eyeColour: EyeColour,
 hairColour: HairColour */

class Guess (character:Character){

  def guessHasHair: Boolean = {
    character.hasHair
  }
  def guessHasFacialHair:Boolean = {
    character.hasFacialHair
  }
  def guessHasGlasses: Boolean = {
    character.hasGlasses
  }
  def guessHasHat: Boolean = {
    character.hasHat
  }

  def guessGender(guess:String): Boolean = {
    guess.toLowerCase() match {
      case "male" => (character.gender == Gender.MALE)
      case "female" => (character.gender == Gender.FEMALE)
      case _ => false
    }
  }

  def guessEyeColour(guess:String): Boolean = {
    guess.toLowerCase() match {
      case "green" => (character.eyeColour == EyeColour.GREEN)
      case "blue" => (character.eyeColour == EyeColour.BLUE)
      case "brown" => (character.eyeColour == EyeColour.BROWN)
      case _ => false
    }
  }

  def guessHairColour(guess:String): Boolean = {
    guess.toLowerCase() match {
      case "blonde" => (character.hairColour == HairColour.BLONDE)
      case "red" => (character.hairColour == HairColour.RED)
      case "brunette" => (character.hairColour == HairColour.BRUNETTE)
      case _ => false
    }
  }

  def guessName(guess: String): Boolean = {
    guess.toLowerCase() == character.name.toLowerCase()
  }
}
