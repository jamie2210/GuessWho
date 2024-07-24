package GuessWho

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class InterfaceSpec extends AnyWordSpec with Matchers{
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


  "displayCharacters" should {
    "Display list of characters and their attributes" when {
      "Always" in {
        assert(interface.displayCharacters(characters) == println(s"Number of remaining characters: ${characters.length}"))
      }
    }
  }

  "isInteger" should{
    "return Left" when{
      "input string cannot convert to integer" in {
        assert(interface.isInteger("hello") == Left(GuessWhoError.AttributeNotInteger))
      }
    }
    "return Right" when{
      "input string can convert to integer" in {
        assert(interface.isInteger("2") == Right(2))
      }
    }
  }

  "isInRange" should {
    "return a Left" when{
      "The input integer is greater than max bound" in {
        assert(interface.isInRange(5, 0, 3) == Left(GuessWhoError.AttributeOutOfRange))
      }
      "The input integer is smaller than min bound" in {
        assert(interface.isInRange(-1, 0, 3) == Left(GuessWhoError.AttributeOutOfRange))
      }
    }
    "return a Right" when {
      "Input integer is equal to min bound" in {
        assert(interface.isInRange(0,0,3) == Right(0))
      }
      "Input integer is equal to max bound" in {
        assert(interface.isInRange(3,0,3) == Right(3))
      }
      "Input integer is within the min and max bounds" in {
        assert(interface.isInRange(2,0,3) == Right(2))
      }
    }


  }

  "matchToAttributeChoice" should {
    "return a Left" when {
      "input not in range 1-8" in {
        assert(interface.matchToAttributeChoice(9) == Left(GuessWhoError.SomethingWentWrongError))
        assert(interface.matchToAttributeChoice(0) == Left(GuessWhoError.SomethingWentWrongError))
      }
    }
    "return a Right" when {
      "input is in range" in {
        assert(interface.matchToAttributeChoice(4) == Right(AttributeChoice.GlassesChoice))
      }
      "input is 0" in {
        assert(interface.matchToAttributeChoice(1) == Right(AttributeChoice.NameChoice))
      }
      "input is 8" in {
        assert(interface.matchToAttributeChoice(8) == Right(AttributeChoice.HintChoice))
      }
    }
  }

  "validateAttributeChoice" should {
    "return Left" when{
      "input string cannot convert to integer" in {
        assert(interface.validateAttributeChoice("hello") == Left(GuessWhoError.AttributeNotInteger))
      }
      "The input as integer is greater than max bound" in {
        assert(interface.validateAttributeChoice("9") == Left(GuessWhoError.AttributeOutOfRange))
      }
      "The input as integer is smaller than min bound" in {
        assert(interface.validateAttributeChoice("0") == Left(GuessWhoError.AttributeOutOfRange))
      }

    }
    "return Right" when {
      "input string can convert to integer and is in range 1-8" in {
        assert(interface.validateAttributeChoice("2") == Right(AttributeChoice.HairChoice))
      }
    }
  }

  "validateQuestionChoice" should {
    "return Left" when{
      "input string cannot convert to integer" in {
        assert(interface.validateQuestionChoice("hello", 0, 4) == Left(GuessWhoError.AttributeNotInteger))
      }
      "The input as integer is greater than max bound" in {
        assert(interface.validateQuestionChoice("9", 0, 4) == Left(GuessWhoError.AttributeOutOfRange))
      }
      "The input as integer is smaller than min bound" in {
        assert(interface.validateQuestionChoice("-1", 0, 4) == Left(GuessWhoError.AttributeOutOfRange))
      }
    }
    "return Right" when {
      "input string can convert to integer and is in range" in {
        assert(interface.validateQuestionChoice("2", 0 , 4) == Right(2))
      }
    }
  }
}
