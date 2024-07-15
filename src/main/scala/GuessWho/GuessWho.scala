package GuessWho
import scala.io.StdIn.readLine


object GuessWho extends App{

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

  private val characterList:Seq[Character] = Seq(jon, tony, bill, paul, gary, steve, jess, vicky, sarah, beff)

  private val game:GameBoard = new GameBoard(characters = characterList, defaultChosenCharacter = None)

  private var _playersRemaining:Seq[Character] = game.getRemainingCharacters

  private var _newTurn:Boolean = true
  private var _winner:Boolean = false

  private var _hairQuestions:Map[Int, String] = Map(
    1 -> "Do they have hair?",
    2 -> "Do they have blonde hair?",
    3 -> "Do they have brunette hair?",
    4 -> "Do they have red hair?",
    0 -> "Go back"
  )

  private var _eyeQuestions:Map[Int, String] = Map(
    1 -> "Do they have blue eyes?",
    2 -> "Do they have brown eyes?",
    3 -> "Do they have green eyes?",
    0 -> "Go back"
  )

  private var _genderQuestions:Map[Int, String] = Map(
    1 -> "Are the male?",
    2 -> "Are the female?",
    0 -> "Go back"
  )

  private var _facialQuestions:Map[Int, String] = Map(
    1 -> "Do they have facial hair?",
    0 -> "Go back"
  )

  private var _glassesQuestions:Map[Int, String] = Map(
    1 -> "Do they have glasses?",
    0 -> "Go back"
  )

  private var _hatQuestions:Map[Int, String] = Map(
    1 -> "Do they have a hat?",
    0 -> "Go back"
  )


  def displayCharacters(characters:Seq[Character]):Unit = {
    _playersRemaining.foreach { character =>
      println(
        s"""
           |Name: ${character.name}
           |Has Hair: ${character.hasHair}
           |Has Facial Hair: ${character.hasFacialHair}
           |Has Glasses: ${character.hasGlasses}
           |Has Hat: ${character.hasHat}
           |Gender: ${character.gender}
           |Eye Colour: ${character.eyeColour}
           |Hair Colour: ${character.hairColour}
    """.stripMargin
      )
    }
    println(s"Number of remaining characters: ${_playersRemaining.length}")
  }


  def get_attribute_choice():Int = {
    println(
      s"""
         |1. Name
         |2. Hair
         |3. Facial Hair
         |4. Glasses
         |5. Hat
         |6. Gender
         |7. Eyes
         |
         |8. Get Hint
    """.stripMargin
    )

    print("Enter the number of your choice (e.g. '4' to ask about glasses): ")
    val attribute_choice = readLine()
    try{
      attribute_choice.toInt
    } catch{
      case e: NumberFormatException => {
        println("Invalid choice, please select number")
        get_attribute_choice()}
    }
  }

  def get_name_choice: String = {
    println("Enter 0 to go back or")
    print("Enter the name of the character you think it is:  ")
    val name = readLine()
    name

  }

  def get_question_choice(questions: Map[Int, String]): Int = {
    questions.foreach {
    case (key, value) => println(s"$key : $value")}

    print("Enter the number of the question you'd like to ask: ")
    val attribute_choice = readLine()
    try{
      attribute_choice.toInt
    } catch{
      case e: NumberFormatException => {
        println("Invalid choice, please select number")
        get_question_choice(questions)}
    }
  }

  def check_user_guess(guess:Int):Int = {
    val filterNumber:Int = guess match {
      case 2 => get_question_choice(_hairQuestions) match {
        case 0 => 0
        case 1 => 1
        case 2 => 10
        case 3 => 9
        case 4 => 11
        case _ => -1
      }

      case 3 => get_question_choice(_facialQuestions) match {
        case 0 => 0
        case 1 => 2
        case _ => -1
      }
      case 4 => get_question_choice(_glassesQuestions) match {
        case 0 => 0
        case 1 => 3
        case _ => -1
      }
      case 5 => get_question_choice(_hatQuestions) match {
        case 0 => 0
        case 1 => 4
        case _ => -1
      }
      case 6 => get_question_choice(_genderQuestions) match {
        case 0 => 0
        case 1 => 5
        case 2 => 5
        case _ => -1
      }
      case 7 => get_question_choice(_eyeQuestions) match {
        case 0 => 0
        case 1 => 6
        case 2 => 8
        case 3 => 7
        case _ => -1
      }
      case _ => -1
    }
    val validGuess:Int = if (filterNumber == -1){
      println("Invalid input. Please enter question number only")
    check_user_guess(guess)} else filterNumber

    validGuess
  }

  def user_turn():Boolean = {

    if(_newTurn){
      displayCharacters(_playersRemaining)
      _newTurn = false
    }
    val attribute = get_attribute_choice()
    val guessNumber:Int = check_user_guess(attribute)
    // returns -1 for invalid question input, 0 for go back, other is fine
    if (guessNumber == 0) user_turn()


    val filteredPlayers = game.filterRemaining(guessNumber)
    _playersRemaining = _playersRemaining.filter(x => filteredPlayers.exists(_.name == x.name))
    _winner = if (_playersRemaining.length == 1){
      true
    }else{
      false
    }
    _newTurn = true
    if(!_winner){user_turn()}else{false}

  }
  user_turn()
  println("Well done!!")
}
