import { Component } from '@angular/core';
import { SignalService } from '../signal.service';

@Component({
  selector: 'app-bedrooom2',
  templateUrl: './bedrooom2.component.html',
  styleUrl: './bedrooom2.component.css'
})
export class Bedrooom2Component {

  constructor(public readonly signalService: SignalService) {

  }

  
}
