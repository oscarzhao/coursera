class Phone {
  // Quoted from 
  // Lutz Prechelt: An Empirical Comparison of Seven Programming Languages
  val mnenomics = Map(
    '2' -> "ABC",
    '3' -> "DEF",
    '4' -> "GHI",
    '5' -> "JKL",
    '6' -> "MNO",
    '7' -> "PQRS",
    '8' -> "TUV",
    '9' -> "WXYZ",
  )

  // translate produces all phrases that can serve as mnenomics for the phone number
  def translate(phoneNumber: String): String = {
    phoneNumber
  }

  def test_translate() = {
    val input = "7225247386"
    val ret = translate(input)
    print("input: $input, ret: $ret")
  }
}