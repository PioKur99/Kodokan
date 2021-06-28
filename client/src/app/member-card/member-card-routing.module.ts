import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChooseDisciplineComponent } from './choose-discipline/choose-discipline.component';
import { MemberCardComponent } from './member-card.component';
import { MessageComponent } from './message/message.component';
import { PreparedCardComponent } from './prepared-card/prepared-card.component';

const routes: Routes = [{ path: '', component: MemberCardComponent,
children: [
  {path: '', redirectTo: 'message', pathMatch: 'full' },
  {path: 'message', component: MessageComponent, data: {text: 'message'} },
  {path: 'choose-discipline', component: ChooseDisciplineComponent, data: {text: 'choose-discipline'} },
  {path: 'prepared-card', component: PreparedCardComponent, data: {text: 'prepared-card'} }] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberCardRoutingModule { }
