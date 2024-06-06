import { Component, OnInit } from '@angular/core';
import { WritableSignal, signal } from '@angular/core';
import { SignalService } from '../signal.service';

@Component({
  selector: 'app-bedroom1',
  templateUrl: './bedroom1.component.html',
  styleUrl: './bedroom1.component.css'
})
export class Bedroom1Component implements OnInit {

  // counter: WritableSignal<number> = signal(0);

  // injecting the Signal Service
  constructor(public readonly signalService: SignalService) {

  }

  ngOnInit(): void { 
  }

  increase(): void {
    // this.counter.set(this.counter() + 1);

    // this.counter.update(cnt => cnt + 1);

    this.signalService.updateCounter();
  }
}
