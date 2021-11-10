import { Component, OnInit } from '@angular/core';
import {PolaganjeService} from "../services/polaganje.service";
import {Test} from "../model/Test";
import {Question} from "../model/Question";
import {Answer} from "../model/Answer";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from 'sweetalert2'
@Component({
  selector: 'app-ucenik-polaganje',
  templateUrl: './ucenik-polaganje.component.html',
  styleUrls: ['./ucenik-polaganje.component.css']
})
export class UcenikPolaganjeComponent implements OnInit {

  test = new Test(null);
  testName: string;
  private sub: any;

  pager = {
    index: 0,
    size: 1,
    count: 1
  };

  constructor(private polaganjeService: PolaganjeService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {

      let request = {
        studentUsername: localStorage.getItem('currentuser').toString(),
        testTypeId: +params.attemptID
      }

      this.startTest(request);
    });

  }

  startTest(request: any) {
    this.polaganjeService.startTest(request).subscribe(res => {
      this.test = new Test(res);
    });
  }


  // tslint:disable-next-line:typedef
  onSelect(question: Question, answer: Answer) {
    question.answers.forEach((x) => {
      if (x.answerID !== answer.answerID) x.selected = false;
    });
  }

  // tslint:disable-next-line:typedef
  goTo(index: number) {
    if (index >= 0 && index < this.pager.count) {
      this.pager.index = index;
    }
  }

  // tslint:disable-next-line:typedef
  isAnswered(question: Question) {
    return question.answers.find(x => x.selected) ? 'Answered' : 'Not Answered';
  }


  // tslint:disable-next-line:typedef
  onSubmit() {
    let submitRequest = {
      testAttemptId: this.test.attemptID,
      answers: []
    }

    this.test.questions.forEach(x => {
      x.answers.forEach(a => {
        if (a.selected) {
          submitRequest.answers.push({answerId: a.answerID});
        }
      });
    });

    this.polaganjeService.submitTest(submitRequest).subscribe(res => {

      if(res['passed']){

        Swal.fire({
          icon: 'success',
          title: 'Čestitamo',
          text: 'Položili ste test!',
        });

      }else{
        Swal.fire({
          icon: 'error',
          title: 'Više sreće drugi put',
          text: 'Niste položili test.',
        });

      }


    });


  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);
  }
}
