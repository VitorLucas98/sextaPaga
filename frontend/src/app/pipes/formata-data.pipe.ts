import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formataData'
})
export class FormataDataPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}

