package datapath
import chisel3.iotesters.PeekPokeTester
class Alu_t(c:Alu) extends PeekPokeTester(c){
    poke(c.io.a,8)
    poke(c.io.b,8)
    poke(c.io.alucontrol,8)
    step(1)
    expect(c.io.out,0)
}
