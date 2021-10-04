import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ativoInativo'
})
export class AtivoInativoPipe implements PipeTransform {

  transform(status: boolean): string {
    return status ? 'Ativo' : 'Inativo';
  }

}
