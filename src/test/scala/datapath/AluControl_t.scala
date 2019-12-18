package datapath
import chisel3.iotesters.PeekPokeTester
class AluControl_t(c:AluControl) extends PeekPokeTester(c){
    poke(c.io.aluop,2)
    poke(c.io.func3,5)
    poke(c.io.func7,0)
    step(1)
    expect(c.io.alucntrl,21)
}