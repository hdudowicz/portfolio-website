import { Component, Input, OnInit, OnDestroy, ViewChild, ElementRef, NgZone } from '@angular/core';

interface TextItem {
  text: string;
  displayTime: number;
}

@Component({
  selector: 'app-scramble-text-effect',
  template: '<p class="whitespace-pre-line text-center" #textElement></p>',
})
export class ScrambleTextEffectComponent implements OnInit, OnDestroy {
  @Input() textItems: TextItem[] = [];
  @ViewChild('textElement', { static: true }) textElementRef!: ElementRef;

  private el!: HTMLElement;
  private chars: string = '!<>-_\\/[]{}—=+*^?#________';
  private queue: any[] = [];
  private frame: number = 0;
  private frameRequest: number = 0;
  private currentIndex: number = 0;

  constructor(private ngZone: NgZone) {}

  ngOnInit() {
    this.el = this.textElementRef.nativeElement;
    this.nextText();
  }

  ngOnDestroy() {
    if (this.frameRequest) {
      cancelAnimationFrame(this.frameRequest);
    }
  }

  private setText(newText: string): Promise<void> {
    const oldText = this.el.innerText;
    const length = Math.max(oldText.length, newText.length);
    const promise = new Promise<void>((resolve) => {
      this.queue = [];
      for (let i = 0; i < length; i++) {
        const from = oldText[i] || '';
        const to = newText[i] || '';
        const start = Math.floor(Math.random() * 40);
        const end = start + Math.floor(Math.random() * 40) + Math.floor(newText.length / 2);
        this.queue.push({ from, to, start, end });
      }
      if (this.frameRequest) {
        cancelAnimationFrame(this.frameRequest);
      }
      this.frame = 0;
      this.update(resolve);
    });
    return promise;
  }

  private update(resolve: () => void): void {
    let output = '';
    let complete = 0;
    for (let i = 0; i < this.queue.length; i++) {
      let { from, to, start, end, char } = this.queue[i];
      if (this.frame >= end) {
        complete++;
        output += to;
      } else if (this.frame >= start) {
        if (!char || Math.random() < 0.28) {
          char = this.randomChar();
          this.queue[i].char = char;
        }
        output += char;
      } else {
        output += from;
      }
    }
    this.el.innerHTML = output;
    if (complete === this.queue.length) {
      resolve();
    } else {
      this.ngZone.runOutsideAngular(() => {
        this.frameRequest = requestAnimationFrame(() => this.update(resolve));
      });
      this.frame++;
    }
  }

  private randomChar(): string {
    return this.chars[Math.floor(Math.random() * this.chars.length)];
  }

  private nextText(): void {
    if (this.currentIndex < this.textItems.length) {
      const currentItem = this.textItems[this.currentIndex];
      this.setText(currentItem.text).then(() => {
        this.currentIndex++;
        if (this.currentIndex < this.textItems.length) {
          setTimeout(() => this.nextText(), currentItem.displayTime);
        }
      });
    }
  }
}