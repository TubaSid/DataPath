package datapath

import chisel3._
import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val tests = Map(
    "InstructionTypeDecode" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new InstructionTypeDecode(), manager) {
        (c) => new InstructionTypeDecode_t(c)
      }
    },
    "ControlDecode" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new ControlDecode(), manager) {
        (c) => new ControlDecode_t(c)
      }
    },
    "Pc" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Pc(), manager) {
        (c) => new Pc_t(c)
      }
    },
    "Control" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Control(), manager) {
        (c) => new Control_t(c)
      }
    },
      "ImmediateGeneration" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new ImmediateGeneration(), manager) {
        (c) => new ImmediateGeneration_t(c)
      }
    },
     "Jalr" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Jalr(), manager) {
        (c) => new Jalr_t(c)
      }
    },
      "AluControl" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new AluControl(), manager) {
        (c) => new AluControl_t(c)
      }
    },
    "DataMemory" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new DataMemory(), manager) {
        (c) => new DataMemory_t(c)
      }
    },
     "InstructionMemory" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new InstructionMemory(), manager) {
        (c) => new InstructionMemory_t(c)
      }
    },
        "RegFile" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new RegFile(), manager) {
        (c) => new RegFile_t(c)
      }
    },
   "Alu" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Alu(), manager) {
        (c) => new Alu_t(c)
      }
    },
     "Top" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Top(), manager) {
        (c) => new Top_t(c)
      }
    }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("datapath", tests, args)
  }
}