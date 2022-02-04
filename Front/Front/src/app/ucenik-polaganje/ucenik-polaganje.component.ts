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
  question :any;
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
      this.initState();
      this.firstQuestion();
    });

  }

  startTest(request: any) {
    this.polaganjeService.startTest(request).subscribe(res => {
      this.test = new Test(res);
      this.pager.count = this.test.questions.length;
    });
    
  }

  initState(){
    let initRequest = {

      studentUsername: localStorage.getItem('currentuser').toString(),
      knowledgeSpaceId: 1

    };
    
    this.polaganjeService.initState(initRequest).subscribe(res => {
      console.log("INIT");
      console.log(res);
    });;

  }

  firstQuestion(){
    let studentRequest = {

      studentUsername: localStorage.getItem('currentuser').toString(),
      knowledgeSpaceId: 1

    }
    this.polaganjeService.firstQuestion(studentRequest).subscribe(res => {
      console.log("FQ");
      console.log(res);

      this.question = res;
      
    });;


  }


  // tslint:disable-next-line:typedef
  onSelect(question: Question, answer: Answer) {
    question.answers.forEach((x) => {
      console.log(x);
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
 
    let answer: any;
    this.question.answers.forEach((x) => {
      console.log(x);
      if (x.selected) answer = x;
    });
    let answerRequest = {

      id: answer.id,
      studentUsername: localStorage.getItem('currentuser').toString(),
      questionId: this.question.id

    }
    this.polaganjeService.nextQuestion(answerRequest).subscribe(res => {
      console.log("NQ");
      console.log(res);

      this.question = res;
    });;



  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);
  }
}
