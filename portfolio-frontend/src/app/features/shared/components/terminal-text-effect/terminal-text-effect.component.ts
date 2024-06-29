import { Component, OnInit, Input, ElementRef, ViewChild } from '@angular/core';

interface TextItem {
  text: string;
  color: string;
}

@Component({
  selector: 'app-terminal-text-effect',
  template: `
    <div class='console-container text-4xl font-bold'>
      <span #textElement></span>
      <div class='console-underscore' #consoleElement>&#95;</div>
    </div>
  `,
  styles: [`
    .console-container {
      display: block;
      color: white;
      margin: auto;
      font-family: 'Courier New', Courier, monospace;
    }
    .console-underscore {
      display: inline-block;
      position: relative;
      top: -0.14em;
      left: 0.1em;
    }
    .hidden {
      opacity: 0;
    }
  `]
})
export class TerminalTextEffectComponent implements OnInit {
  @Input() textItems: TextItem[] = []
  
  @ViewChild('textElement', { static: true }) textElement!: ElementRef;
  @ViewChild('consoleElement', { static: true }) consoleElement!: ElementRef;

  private letterCount = 1;
  private x = 1;
  private waiting = false;
  private visible = true;

  ngOnInit() {
    this.consoleText();
    this.blinkUnderscore();
  }

  private consoleText() {
    const target = this.textElement.nativeElement;
    target.style.color = this.textItems[0].color;

    setInterval(() => {
      if (this.letterCount === 0 && !this.waiting) {
        this.waiting = true;
        target.innerHTML = this.textItems[0].text.substring(0, this.letterCount);
        setTimeout(() => {
          const usedItem = this.textItems.shift();
          this.textItems.push(usedItem!);
          this.x = 1;
          target.style.color = this.textItems[0].color;
          this.letterCount += this.x;
          this.waiting = false;
        }, 1000);
      } else if (this.letterCount === this.textItems[0].text.length + 1 && !this.waiting) {
        this.waiting = true;
        setTimeout(() => {
          this.x = -1;
          this.letterCount += this.x;
          this.waiting = false;
        }, 1000);
      } else if (!this.waiting) {
        target.innerHTML = this.textItems[0].text.substring(0, this.letterCount);
        this.letterCount += this.x;
      }
    }, 120);
  }

  private blinkUnderscore() {
    setInterval(() => {
      if (this.visible) {
        this.consoleElement.nativeElement.className = 'console-underscore hidden';
        this.visible = false;
      } else {
        this.consoleElement.nativeElement.className = 'console-underscore';
        this.visible = true;
      }
    }, 400);
  }
}