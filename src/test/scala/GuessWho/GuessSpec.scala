package GuessWho


import org.scalatest.flatspec.AnyFlatSpec
/** name: String,
 hasHair: Boolean,
 hasFacialHair: Boolean,
 hasGlasses: Boolean,
 hasHat: Boolean,
 gender:Gender,
 eyeColour: EyeColour,
 hairColour: HairColour */

class GuessSpec extends AnyFlatSpec {
  val jamie: Character = Character(name = "Jamie", hasHair = false, hasFacialHair = true, hasGlasses = true, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.BLONDE)
  val tom: Character = Character(name = "Tom", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = Gender.MALE, eyeColour = EyeColour.GREEN, hairColour = HairColour.BLONDE)
  val vicky: Character = Character(name = "Vicky", hasHair = true, hasFacialHair = false, hasGlasses = false, hasHat = true, gender = Gender.FEMALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.RED)
  val jane: Character = Character(name = "Jane", hasHair = false, hasFacialHair = false, hasGlasses = false, hasHat = false, gender = Gender.FEMALE, eyeColour = EyeColour.BLUE, hairColour = HairColour.NONE)
  val guessJamie: Guess = new Guess(jamie)
  val guessTom: Guess = new Guess(tom)
  val guessVicky: Guess = new Guess(vicky)

  val characters: Seq[Character] = Seq(tom, jamie, vicky, jane)

  val testGame: GameBoard = new GameBoard(characters = characters, defaultChosenCharacter = Some(jamie))
  val interface: Interface = new Interface()

  "guessHasHair" should "return true if character has hair, false if they don't" in {
    assert(!guessJamie.guessHasHair)
    assert(guessTom.guessHasHair)
    assert(guessVicky.guessHasHair)
  }

  // Does Character have facial hair test
  "hasFacialHair" should "return true if character has facial hair, false if they don't" in {
    assert(guessJamie.guessHasFacialHair)
    assert(!guessTom.guessHasFacialHair)
    assert(!guessVicky.guessHasFacialHair)
  }

  // Does Character have glasses test
  "guessGlasses" should "return true if character has glasses, false if they don't" in {
    assert(guessJamie.guessHasGlasses)
    assert(guessTom.guessHasGlasses)
    assert(!guessVicky.guessHasGlasses)
  }

  // Does Character has Hat test
  "guessHat" should "return true if character has hat, false if they don't" in {
    assert(guessJamie.guessHasHat)
    assert(!guessTom.guessHasHat)
    assert(guessVicky.guessHasHat)
  }

  // Is character Male or Female
  "guessGender" should "return true if both character and guess is Male, and vice versa" in {
    assert(guessJamie.guessGender(Gender.MALE))
    assert(guessJamie.guessGender(Gender.MALE))
    assert(!guessTom.guessGender(Gender.FEMALE))
    assert(guessVicky.guessGender(Gender.FEMALE))
  }

  // What eye colour does the character have
  "guessEyeColour" should "return true if correct eye colour is guessed" in {
    assert(guessJamie.guessEyeColour(EyeColour.BROWN))
    assert(guessTom.guessEyeColour(EyeColour.GREEN))
    assert(guessVicky.guessEyeColour(EyeColour.BROWN))
    assert(guessVicky.guessEyeColour(EyeColour.BROWN))
    assert(!guessJamie.guessEyeColour(EyeColour.GREEN))
  }

  // What hair colour does the character have
  "guessHairColour" should "return true if correct hair colour is guessed" in {
    assert(guessJamie.guessHairColour(HairColour.BLONDE))
    assert(guessTom.guessHairColour(HairColour.BLONDE))
    assert(guessVicky.guessHairColour(HairColour.RED))
    assert(!guessTom.guessHairColour(HairColour.RED))
  }

  // Guess name of character
  "guessName" should "respond true or false depending on guess" in {
    assert(guessJamie.guessName("jamie"))
    assert(!guessJamie.guessName("tom"))
  }

}

