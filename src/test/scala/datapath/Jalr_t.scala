package datapath
import chisel3.iotesters.PeekPokeTester
class Jalr_t(c:Jalr) extends PeekPokeTester(c){
    poke(c.io.a,4)
    poke(c.io.b,8)
   // expect(c.io.out,-24)
}