package com.github.skozlov.lamca.core

sealed trait Term

case class Variable(name: String) extends Term

case class Application(function: Term, argument: Term) extends Term

case class Abstraction(variable: Variable, body: Term) extends Term