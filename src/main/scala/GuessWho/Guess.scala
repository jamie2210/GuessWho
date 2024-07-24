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

//  Return value of boolean params

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

//  Return true if gender of chosen character is equal to the guess
  def guessGender(gender: Gender): Boolean = {
    gender match {
      case Gender.MALE => character.gender == Gender.MALE
      case Gender.FEMALE => character.gender == Gender.FEMALE
    }
  }

//  Return true if eye colour of chosen character is equal to the guessed colour
  def guessEyeColour(eyeColour: EyeColour): Boolean = {
    eyeColour match {
      case EyeColour.BLUE => character.eyeColour == EyeColour.BLUE
      case EyeColour.GREEN => character.eyeColour == EyeColour.GREEN
      case EyeColour.BROWN => character.eyeColour == EyeColour.BROWN
    }
  }

  //  Return true if hair colour of chosen character is equal to the guessed colour
  def guessHairColour(hairColour: HairColour): Boolean = {
    hairColour match {
      case HairColour.BLONDE => character.hairColour == HairColour.BLONDE
      case HairColour.BRUNETTE => character.hairColour == HairColour.BRUNETTE
      case HairColour.RED => character.hairColour == HairColour.RED
      case HairColour.NONE => character.hairColour == HairColour.NONE
    }
  }

  //  Return true if the name of chosen character is equal to the guessed name
  def guessName(guess: String): Boolean = {
    guess.toLowerCase() == character.name.toLowerCase()
  }
}
