package datapath
import chisel3.iotesters.PeekPokeTester
class Top_t(c:Top) extends PeekPokeTester(c){
    step(50)
}