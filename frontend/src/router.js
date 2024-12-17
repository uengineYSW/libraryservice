
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BookManagementBookManager from "./components/listers/BookManagementBookCards"
import BookManagementBookDetail from "./components/listers/BookManagementBookDetail"

import BookListView from "./components/BookListView"
import BookListViewDetail from "./components/BookListViewDetail"
import BookDetailsView from "./components/BookDetailsView"
import BookDetailsViewDetail from "./components/BookDetailsViewDetail"
import LoanmanagementLoanManager from "./components/listers/LoanmanagementLoanCards"
import LoanmanagementLoanDetail from "./components/listers/LoanmanagementLoanDetail"

import LoanHistoryView from "./components/LoanHistoryView"
import LoanHistoryViewDetail from "./components/LoanHistoryViewDetail"
import LoanDetailsView from "./components/LoanDetailsView"
import LoanDetailsViewDetail from "./components/LoanDetailsViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/bookManagements/books',
                name: 'BookManagementBookManager',
                component: BookManagementBookManager
            },
            {
                path: '/bookManagements/books/:id',
                name: 'BookManagementBookDetail',
                component: BookManagementBookDetail
            },

            {
                path: '/bookManagements/bookLists',
                name: 'BookListView',
                component: BookListView
            },
            {
                path: '/bookManagements/bookLists/:id',
                name: 'BookListViewDetail',
                component: BookListViewDetail
            },
            {
                path: '/bookManagements/bookDetails',
                name: 'BookDetailsView',
                component: BookDetailsView
            },
            {
                path: '/bookManagements/bookDetails/:id',
                name: 'BookDetailsViewDetail',
                component: BookDetailsViewDetail
            },
            {
                path: '/loanmanagements/loans',
                name: 'LoanmanagementLoanManager',
                component: LoanmanagementLoanManager
            },
            {
                path: '/loanmanagements/loans/:id',
                name: 'LoanmanagementLoanDetail',
                component: LoanmanagementLoanDetail
            },

            {
                path: '/loanmanagements/loanHistories',
                name: 'LoanHistoryView',
                component: LoanHistoryView
            },
            {
                path: '/loanmanagements/loanHistories/:id',
                name: 'LoanHistoryViewDetail',
                component: LoanHistoryViewDetail
            },
            {
                path: '/loanmanagements/loanDetails',
                name: 'LoanDetailsView',
                component: LoanDetailsView
            },
            {
                path: '/loanmanagements/loanDetails/:id',
                name: 'LoanDetailsViewDetail',
                component: LoanDetailsViewDetail
            },


    ]
})
