package datapath
import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
class ImmediateGeneration extends Module{
    val io=IO(new Bundle{
        val insn=Input(UInt(32.W))
        val pc=Input(SInt(32.W))
        val s_imm=Output(SInt(32.W))
        val sb_imm=Output(SInt(32.W))
        val u_imm=Output(SInt(32.W))
        val uj_imm=Output(SInt(32.W))
        val i_imm=Output(SInt(32.W))
    })
    //s_imm
    val s_lbits=io.insn(11,7)
    val s_ubits=io.insn(31,25)
    val s_12bits=Cat(s_ubits,s_lbits)
    val s_32bits=Cat(Fill(20,s_12bits(11)),s_12bits)
    io.s_imm:=s_32bits.asSInt
    //sb_imm
    val sb_lbits=io.insn(11,8)
    val sb_11=io.insn(7)
    val sb_ubits=io.insn(30,25)
    val sb_12=io.insn(31)
    val sb_12bits=Cat(sb_12,sb_11,sb_ubits,sb_lbits,0.U)
    val sb_32bits=Cat(Fill(20,(sb_12bits(11))), sb_12bits).asSInt
    io.sb_imm:=sb_32bits+io.pc
    //u_imm
    val u_20bits=io.insn(31,12)
    val u_32bits=Cat(Fill(12,u_20bits(19)),u_20bits).asSInt
    io.u_imm:=u_32bits << 12.U 
    //uj_imm
    val uj_20=io.insn(31)
    val uj_ubits=io.insn(30,21)
    val uj_11=io.insn(20)
    val uj_lbits=io.insn(19,12)
    val uj_20bits=Cat(uj_20,uj_lbits,uj_11,uj_ubits,0.U)
    val uj_32bits=Cat(Fill(12,uj_20bits(19)),uj_20bits).asSInt
    io.uj_imm:=uj_32bits+io.pc
    //i_imm
    val i_20bits=io.insn(31,20)
    val i_32bits=Cat(Fill(20,i_20bits(11)),i_20bits)
    io.i_imm:=i_32bits.asSInt
}
