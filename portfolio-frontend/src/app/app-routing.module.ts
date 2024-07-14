import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { AboutComponent } from './features/about/about.component';
import { ProjectsComponent } from './features/projects/projects.component';
import { ContactComponent } from './features/contact/contact.component';
import {CreateArticleComponent} from "./features/create-article/create-post.component";
import {AccessDeniedComponent} from "./features/shared/components/access-denied/access-denied.component";
import {AdminAuthGuard} from "./features/shared/guards/auth.guard";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'create-article', component: CreateArticleComponent, canActivate: [AdminAuthGuard] },
  { path: 'access-denied', component: AccessDeniedComponent },
  { path: 'contact', component: ContactComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
