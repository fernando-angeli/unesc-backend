import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { EmprestimoController } from './controller/emprestimo.controller';
import { EmprestimoEntity } from './entity/emprestimo.entity';
import { EmprestimoService } from './service/emprestimo.service';

@Module({
  imports: [TypeOrmModule.forFeature([EmprestimoEntity])],
  controllers: [EmprestimoController],
  providers: [EmprestimoService],
})
export class EmprestimoModule {}
