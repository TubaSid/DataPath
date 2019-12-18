package datapath
import chisel3._
class Control extends Module{
    val io=IO(new Bundle{
        val opcode=Input(UInt(7.W))
        val memwrite=Output(UInt(1.W))
        val branch=Output(UInt(1.W))
        val memread=Output(UInt(1.W))
        val regwrite=Output(UInt(1.W))
        val memtoreg=Output(UInt(1.W))
        val aluop=Output(UInt(3.W))
        val operand_a_sel=Output(UInt(2.W))
        val operand_b_sel=Output(UInt(1.W))
        val extend_sel=Output(UInt(2.W))
        val next_pc_sel=Output(UInt(2.W))
    })
    val instruction_type_decode = Module(new InstructionTypeDecode)
    val control_decode = Module(new ControlDecode)
    instruction_type_decode.io.opcode:=io.opcode
    control_decode.io.rtype:=instruction_type_decode.io.rtype
    control_decode.io.loadtype:=instruction_type_decode.io.loadtype
    control_decode.io.stype:=instruction_type_decode.io.stype
    control_decode.io.sbtype:=instruction_type_decode.io.sbtype
    control_decode.io.itype:=instruction_type_decode.io.itype
    control_decode.io.jalrtype:=instruction_type_decode.io.jalrtype
    control_decode.io.jaltype:=instruction_type_decode.io.jaltype
    control_decode.io.luitype:=instruction_type_decode.io.luitype

    io.memwrite:=control_decode.io.memwrite
    io.branch:=control_decode.io.branch
    io.memread:=control_decode.io.memread
    io.regwrite:=control_decode.io.regwrite
    io.memtoreg:=control_decode.io.memtoreg
    io.aluop:=control_decode.io.aluop
    io.operand_a_sel:=control_decode.io.operand_a_sel
    io.operand_b_sel:=control_decode.io.operand_b_sel
    io.extend_sel:=control_decode.io.extend_sel
    io.next_pc_sel:=control_decode.io.next_pc_sel
}