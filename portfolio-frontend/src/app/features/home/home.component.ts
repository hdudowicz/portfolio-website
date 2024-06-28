import { Component, ViewChild } from '@angular/core';
import { ScrambleTextEffectComponent } from '../shared/components/scramble-text-effect/scramble-text-effect.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  @ViewChild(ScrambleTextEffectComponent) private scrambleTextEffect!: ScrambleTextEffectComponent;
  subtitltTextItems = [
    { text: 'Hi!', displayTime: 1000 },
    { text: 'My name is Hubert', displayTime: 2000 },
    { text: 'I\'m a full stack and mobile developer', displayTime: 3500 },
    { text: 'Feel free to explore my projects and the services I offer.\nReach me through the contact page or find me on various platforms in the bottom right of this page.', displayTime: 1000 },
  ];
}
