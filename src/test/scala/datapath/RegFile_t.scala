package datapath
import chisel3.iotesters.PeekPokeTester
class RegFile_t(c:RegFile) extends PeekPokeTester(c){
    step(1)

}
