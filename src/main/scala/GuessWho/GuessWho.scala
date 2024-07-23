package GuessWho
import scala.io.StdIn.readLine


object GuessWho extends App{
//  Define characters
  private val jon: Character = Character(name = "Jon", hasHair = false, hasFacialHair = true, hasGlasses = true, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.NONE)
  private val tony: Character = Character(name = "Tony", hasHair = false, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = Gender.MALE, eyeColour = EyeColour.GREEN, hairColour = HairColour.NONE)
  private val bill: Character = Character(name = "Bill", hasHair = true, hasFacialHair = true, hasGlasses = false, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.BRUNETTE)
  private val paul: Character = Character(name = "Paul", hasHair = true, hasFacialHair = false, hasGlasses = false, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.GREEN, hairColour = HairColour.BRUNETTE)
  private val gary: Character = Character(name = "Gary", hasHair = true, hasFacialHair = true, hasGlasses = false, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.BLONDE)
  private val steve: Character = Character(name = "Steve", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = Gender.MALE, eyeColour = EyeColour.BLUE, hairColour = HairColour.RED)
  private val jess: Character = Character(name = "Jess", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = Gender.FEMALE, eyeColour = EyeColour.BLUE, hairColour = HairColour.BLONDE)
  private val vicky: Character = Character(name = "Vicky", hasHair = true, hasFacialHair = false, hasGlasses = false, hasHat = true, gender = Gender.FEMALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.RED)
  private val sarah: Character = Character(name = "Sarah", hasHair = false, hasFacialHair = false, hasGlasses = false, hasHat = false, gender = Gender.FEMALE, eyeColour = EyeColour.BLUE, hairColour = HairColour.NONE)
  private val beff: Character = Character(name = "Beff", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = true, gender = Gender.FEMALE, eyeColour = EyeColour.GREEN, hairColour = HairColour.BRUNETTE)
// Add characters to list
  private val characterList:Seq[Character] = Seq(jon, tony, bill, paul, gary, steve, jess, vicky, sarah, beff)

// Create new instances of GameBoard and Interface
  private val game:GameBoard = new GameBoard(characters = characterList, defaultChosenCharacter = None)
  private val interface: Interface = new Interface()

// Create private variable to track remaining players
  private var _playersRemaining:Seq[Character] = game.getRemainingCharacters

  // Extension 3:
//  private var _playerOneRemainingCharacters
//  private var _playerTwoRemainingCharacters

//  Private variables to track game state
  private var _newTurn:Boolean = true
  private var _winner:Boolean = false


//  Method to display remaining characters and recent hint message
  private def print_characters(updateMessage: String): Unit = if(_newTurn){
    interface.displayCharacters(_playersRemaining)
    println(updateMessage)
    _newTurn = false
  }

//  Check to see if user chose correct character by name
//  If yes -> they win
//  If no -> continue
  private def filter_by_name(): Seq[Character] = {
    val name: String = interface.get_name_choice()
    if(name == "0") user_turn()
    val filteredPlayers:(Seq[Character], Boolean) = game.filterRemaining(name)
    _playersRemaining = _playersRemaining.filter(x => filteredPlayers._1.exists(_.name == x.name))
    _winner = if (filteredPlayers._2) true else false
    _playersRemaining
  }

// Filter characters by attribute question
  private def filterByAttribute(question: Question):Seq[Character] = {

    val filteredPlayers = game.filterRemaining(question)
    _playersRemaining = _playersRemaining.filter(x => filteredPlayers.exists(_.name == x.name))
    _playersRemaining
  }

  private def user_turn():Boolean = {
    val attribute: AttributeChoice = interface.getAttributeChoice
    attribute match {
      case AttributeChoice.NameChoice => filter_by_name()
      case _ =>
        val question: Question = interface.getQuestionFromAttribute(attribute)
        if (question == Question.GoBackOption) user_turn() else filterByAttribute(question)
    }
    // returns -1 for invalid question input, 0 for go back, other is fine
    _newTurn = true
    print_characters(game.get_update_message())
    game.reset_update_message()
    if(!_winner){user_turn()}else{false}

  }
  print_characters("")
  user_turn()
  println("Well done!!")
}
